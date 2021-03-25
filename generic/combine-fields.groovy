def colors = [];

colors << doc.select_color.tokenize("|")*.trim();
colors << doc.color.tokenize("|")*.trim();

index.ss_colors = colors.flatten().join("|"); 
