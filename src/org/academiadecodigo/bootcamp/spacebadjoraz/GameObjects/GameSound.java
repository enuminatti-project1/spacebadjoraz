package org.academiadecodigo.bootcamp.spacebadjoraz.GameObjects;


public enum GameSound {
    BGM("tank"),
    BGMJORGE("imperial"),
    FERRAOBGM("sephirothalterado"),
    PEW("piu"),
    LOSS("burro"),
    FILIPEINTRO("ehpah"),
    FILIPEDEATH("filipedeath"),
    BRIGHENTIINTRO("brighentiintro"),
    BRIGHENTIDEATH("brighentideath"),
    JORGEINTRO("jorgeintro"),
    JORGEDEATH("jorgedeath"),
    CATARINAINTRO("catarinaintro"),
    CATARINADEATH("catarinadeath"),
    FERRAOINTRO("ferraointro"),
    ENEMYBULLET("pah"),
    WIN("ohnaosei"),
    BALELE("balele"),
    UNDERNIGHT("undernight"),
    BADJORAZ("badjoraz");

    private String name;

    GameSound(String name) {
        this.name = name;
    }

    public String getPath() {
        return "/resources/sound/" + this.name + ".wav";
    }
}
