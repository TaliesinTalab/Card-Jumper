public class Player extends Entities{
    private int strength, intelligence, dexterity, cuteness, body, level, health, fullHealth, healthMod;

    public Player(String name, int y, int x) {
        super(name, y, x);
        this.strength = 1;
        this.intelligence = 1;
        this.dexterity = 1;
        this.cuteness = 10; // cuteness goes down over time (as the MC becomes more traumatised / bloodied)
        this.body = 1;
        this.level = 1;
        this.healthMod = 3;
        calculateFullHealth();
        this.health = this.fullHealth;
    }

    // Setter
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public void setCuteness(int cuteness) {
        this.cuteness = cuteness;
    }
    public void setBody(int body) {
        this.body = body;
    }

    // Getter
    public int getStrength() {return this.strength;}
    public int getIntelligence() {return this.intelligence;}
    public int getDexterity() {return this.dexterity;}
    public int getCuteness() {return this.cuteness;}
    public int getBody() {return this.body;}
    public int getLevel() {return this.level;}
    public int getHealth() {return this.health;}
    public int getFullHealth() {return this.fullHealth;}

    // Additional Functions
    public void levelUp() {
        this.level++;
        calculateFullHealth();
        healToFull();
        System.out.println("You have levelled up!\n" +
                "Your current level is: " + this.level);
    }
    public void cutenessUp() {
        this.cuteness++;
        System.out.println("You become cuter!");
    }
    public void cutenessDown() {
        this.cuteness--;
        System.out.println("You feel less cute...");
    }
    public void calculateFullHealth() {
        this.fullHealth = (this.body*2)+(this.level*5)+healthMod;
    }
    // Returns the user back to their full health..
    public void healToFull() {
        this.health = this.fullHealth;
    }
    // Heals the user by a specified amount.
    //      e.g. a potion healing you by 10.
    // args: int 'amount' is the amount that you will be healed by
    public void heal(int amount) {
        if (this.health + amount < this.fullHealth) {
            this.health += amount;
            System.out.println("Healed to " + this.health);
        }
        else {
            healToFull();
            System.out.println("Healed to full health.");
        }
    }
    // Is meant to be used for items that permanently increase the user's health.
    //      e.g. a potion adding +4 to your full health
    // args: int 'mod' is the amount that will be added to your hp
    public void increaseHealth(int mod) {
        this.healthMod += mod;
        calculateFullHealth();
        heal(mod);
    }
    @Override
    void attack() {
        System.out.println("You attack! (and do nothing lmao)");
    }
}
