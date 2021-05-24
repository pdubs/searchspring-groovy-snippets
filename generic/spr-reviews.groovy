def review = doc.mfield_spr_reviews.str();

def valueMatcher = ( review =~ /meta itemprop=\"ratingValue\" content=\"([-+]?[0-9]*\.?[0-9]+)\"/)

if (valueMatcher && valueMatcher[0] && valueMatcher[0][1]) {
    index.ss_review_value = valueMatcher[0][1];
} else {
    index.ss_review_value = 0;
}

def countMatcher = ( review =~ /meta itemprop=\"reviewCount\" content=\"([-+]?[0-9]*\.?[0-9]+)\"/)

if (countMatcher && countMatcher[0] && countMatcher[0][1]) {
    index.ss_review_count = countMatcher[0][1];
} else {
    index.ss_review_count = 0;
}