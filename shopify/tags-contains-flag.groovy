def tags = doc.tags.str().tokenize(",")*.trim();

if (tags.contains("discontinued")) {
    index.ss_exclude = "1";
}
