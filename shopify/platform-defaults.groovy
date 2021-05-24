// Update this to match the image dimension on thier Shopify Theme's results
// ex: 23180__99539_large.jpeg?v=1545071204 => "large"
def img_size = "600x600";

def getImage = { img, suf ->
    def matches = img =~ /(.+)(\.(?:(?i)jpg|jpeg|gif|png).+)/
    return (matches) ? matches[0][1] + '_' + suf + matches[0][2] : "";
}

index.ss_image = getImage(doc.image.str(), img_size);
def images = doc.images.str().tokenize('|');
if (images.size() > 1) {
    index.ss_image_hover = getImage(images.getAt(1), img_size);
}

def slurper = new groovy.json.JsonSlurper();
def json_variants = slurper.parseText(doc.variants.str());

index.ss_sku = json_variants.first().sku;

index.ss_available = 0;
index.ss_inventory_count = 0;

if (json_variants.length) {
    index.ss_available = (json_variants.any {
        policy = it.inventory_policy.str();
        manage = it.inventory_management.str();
        quantity = it.inventory_quantity.num();
        return (manage == "null" || policy == "continue" || quantity > 0);
    }) ? 1 : 0;
    
    index.ss_inventory_count = json_variants.sum {
        it.inventory_quantity.num();
    }
    
    // def variants_instock = json_variants.findAll {
    //     policy = it.inventory_policy.str();
    //     manage = it.inventory_management.str();
    //     quantity = it.inventory_quantity.num();
    //     return (manage == "null" || policy == "continue" || quantity > 0);
    // }
    //
    // def total = json_variants.size();
    // def instock = variants_instock.size();
    // index.ss_instock_pct = Math.round((instock * 100) / total);
    
    def lowest_variant = json_variants.min { it.price.num() };
    def highest_variant = json_variants.max { it.price.num() };
    
    index.ss_price = lowest_variant.price.num();
    index.ss_retail = lowest_variant.compare_at_price.num();
    
    def low_price = lowest_variant.price.num();
    def high_price = highest_variant.price.num();
    if (low_price < high_price) {
        index.ss_price_range = [low_price, high_price];
    }
    
    if (index.ss_price > 0 && index.ss_price < index.ss_retail) {
        index.ss_on_sale = 1;
        index.ss_pct_off = Math.round(((index.ss_retail - index.ss_price) * 100) / index.ss_retail);
    }
}
