

// remove any character not alphanumeric or dash/space
def removeSpecialChar = {
    return it.toLowerCase().replaceAll(/[^a-z0-9-\s]/, "");
}

def color = doc.variant_color.str();
def name = doc.title.str();

color = removeSpecialChar(color);
name = removeSpecialChar(name);

index.ss_name_pt = name.replaceAll(/${color}/, "").trim();