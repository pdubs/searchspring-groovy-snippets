

if (doc.cached_thumbnail_url.str().contains("PlaceholderForMissingImage")) {
    index.ss_has_image = 0;
} else {
    index.ss_has_image = 1;
}
