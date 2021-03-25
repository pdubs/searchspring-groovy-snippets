def ctgy = doc.categories_hierarchy.str().tokenize("|")*.trim()*.replaceAll(/ *> */, ">");

def ctgyFunc = { n ->
   return ctgy.findAll { it.startsWith(n + ">") }*.replaceAll(/${n}>/, "");
}

index.ss_beer_style_lager = ctgyFunc("Beer>Style>Lager Styles");