package com.example.alexanderst.javajokes;

import java.util.Random;

public class Joker {

    private Random generator = new Random(System.currentTimeMillis());

    //Generate "joke" - sentence of 5 to 15 words randomly selected from array of 100 words
    //(http://members.optusnet.com.au/charles57/Creative/Techniques/random_words.htm)
    //ending with exclamation mark and a smiley face as if it were a really funny joke...
    public String getJoke() {
        final int MIN_WORDS = 5;
        final int MAX_WORDS = 15;
        String res = "";
        int numWords = generator.nextInt(MAX_WORDS - MIN_WORDS + 1) + MIN_WORDS;
        for (int i = 0; i < numWords; i++) {
            String word = mWords[generator.nextInt(mWords.length)];
            if (i > 0) {
                res += " ";
                word = word.toLowerCase();
            }
            res += word;
        }
        return res + "! :)";
    }
    final String[] mWords = {
            "Adult",
            "Aeroplane",
            "Air",
            "Aircraft Carrier",
            "Airforce",
            "Airport",
            "Album",
            "Alphabet",
            "Apple",
            "Arm",
            "Army",
            "Baby",
            "Baby",
            "Backpack",
            "Balloon",
            "Banana",
            "Bank",
            "Barbecue",
            "Bathroom",
            "Bathtub",
            "Bed",
            "Bed",
            "Bee",
            "Bible",
            "Bible",
            "Bird",
            "Bomb",
            "Book",
            "Boss",
            "Bottle",
            "Bowl",
            "Box",
            "Boy",
            "Brain",
            "Bridge",
            "Butterfly",
            "Button",
            "Cappuccino",
            "Car",
            "Car-race",
            "Carpet",
            "Carrot",
            "Cave",
            "Chair",
            "Chess Board",
            "Chief",
            "Child",
            "Chisel",
            "Chocolates",
            "Church",
            "Church",
            "Circle",
            "Circus",
            "Circus",
            "Clock",
            "Clown",
            "Coffee",
            "Coffee-shop",
            "Comet",
            "Compact Disc",
            "Compass",
            "Computer",
            "Crystal",
            "Cup",
            "Cycle",
            "Data Base",
            "Desk",
            "Diamond",
            "Dress",
            "Drill",
            "Drink",
            "Drum",
            "Dung",
            "Ears",
            "Earth",
            "Egg",
            "Electricity",
            "Elephant",
            "Eraser",
            "Explosive",
            "Eyes",
            "Family",
            "Fan",
            "Feather",
            "Festival",
            "Film",
            "Finger",
            "Fire",
            "Floodlight",
            "Flower",
            "Foot",
            "Fork",
            "Freeway",
            "Fruit",
            "Fungus",
            "Game",
            "Garden",
            "Gas",
            "Gate",
            "Gemstone",
            "Girl",
            "Gloves",
            "God",
            "Grapes",
            "Guitar",
            "Hammer",
            "Hat",
            "Hieroglyph",
            "Highway",
            "Horoscope",
            "Horse",
            "Hose",
            "Ice",
            "Ice-cream",
            "Insect",
            "Jet fighter",
            "Junk",
            "Kaleidoscope",
            "Kitchen",
            "Knife",
            "Leather jacket",
            "Leg",
            "Library",
            "Liquid",
            "Magnet",
            "Man",
            "Map",
            "Maze",
            "Meat",
            "Meteor",
            "Microscope",
            "Milk",
            "Milkshake",
            "Mist",
            "Money $$$$",
            "Monster",
            "Mosquito",
            "Mouth",
            "Nail",
            "Navy",
            "Necklace",
            "Needle",
            "Onion",
            "PaintBrush",
            "Pants",
            "Parachute",
            "Passport",
            "Pebble",
            "Pendulum",
            "Pepper",
            "Perfume",
            "Pillow",
            "Plane",
            "Planet",
            "Pocket",
            "Post-office",
            "Potato",
            "Printer",
            "Prison",
            "Pyramid",
            "Radar",
            "Rainbow",
            "Record",
            "Restaurant",
            "Rifle",
            "Ring",
            "Robot",
            "Rock",
            "Rocket",
            "Roof",
            "Room",
            "Rope",
            "Saddle",
            "Salt",
            "Sandpaper",
            "Sandwich",
            "Satellite",
            "School",
            "Sex",
            "Ship",
            "Shoes",
            "Shop",
            "Shower",
            "Signature",
            "Skeleton",
            "Slave",
            "Snail",
            "Software",
            "Solid",
            "Space Shuttle",
            "Spectrum",
            "Sphere",
            "Spice",
            "Spiral",
            "Spoon",
            "Sports-car",
            "Spot Light",
            "Square",
            "Staircase",
            "Star",
            "Stomach",
            "Sun",
            "Sunglasses",
            "Surveyor",
            "Swimming Pool",
            "Sword",
            "Table",
            "Tapestry",
            "Teeth",
            "Telescope",
            "Television",
            "Tennis racquet",
            "Thermometer",
            "Tiger",
            "Toilet",
            "Tongue",
            "Torch",
            "Torpedo",
            "Train",
            "Treadmill",
            "Triangle",
            "Tunnel",
            "Typewriter",
            "Umbrella",
            "Vacuum",
            "Vampire",
            "Videotape",
            "Vulture",
            "Water",
            "Weapon",
            "Web",
            "Wheelchair",
            "Window",
            "Woman",
            "Worm",
            "X-ray",
    };

}
