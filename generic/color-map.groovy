def colors = []
if(doc.tags_color instanceof Collection) {
    colors = doc.tags_color
} else {
    colors = [doc.tags_color]
}

index.ss_color = colors.collect { 
    def idx = it.indexOf(">")
    return idx > 0 ? it.substring(0,  idx) : it
}


def colorMap = [
  "BLUE": "Blue",
  "PINK": "Pink",
  "GREEN": "Green",
  "YELLOW/GOLD": "Yellow-Gold",
  "BLACK/GREY": "Black-Grey",
  "PURPLE": "Purple",
  "RED": "Red",
  "ORANGE": "Orange",
  "orange": "Orange",
  "WHITE/NEUTRAL": "White-Neutral",
  "BROWN": "Brown",
  "PATTERN/MULTI": "Multi",
  "MULTI/PATTERN": "Multi",
  "MULTI": "Multi",
  "Multi-Pattern": "Multi"
];

def it = index.ss_color[0];
if (it != 'Color' && it != 'WHITE/CLEAR') {
    index.ss_color_clean = (colorMap.containsKey(it)) ? colorMap[it] : it;
}
