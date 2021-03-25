def img = doc.pictures.str().tokenize(",");

if (img.size() > 0) { index.ss_thumbnail = img[0]; }
