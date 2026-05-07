package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

/**
 * Visitor 1 — estimates resale gold value of each artifact.
 */
public class GoldAppraiser implements ArtifactVisitor {
    private int totalValue = 0;

    @Override
    public void visit(Weapon weapon) {
        int price = weapon.getValue() + weapon.getAttackBonus() * 5;
        totalValue += price;
        System.out.printf("  [GoldAppraiser] Weapon '%s': base=%d + atk*5=%d → %d gold%n",
                weapon.getName(), weapon.getValue(), weapon.getAttackBonus() * 5, price);
    }
    @Override
    public void visit(Potion potion) {
        int price = potion.getValue() + potion.getHealing() * 2;
        totalValue += price;
        System.out.printf("  [GoldAppraiser] Potion '%s': base=%d + heal*2=%d → %d gold%n",
                potion.getName(), potion.getValue(), potion.getHealing() * 2, price);
    }
    @Override
    public void visit(Scroll scroll) {
        int price = scroll.getValue() * 2;
        totalValue += price;
        System.out.printf("  [GoldAppraiser] Scroll '%s' (%s): rarity x2 → %d gold%n",
                scroll.getName(), scroll.getSpellName(), price);
    }
    @Override
    public void visit(Ring ring) {
        int price = ring.getValue() + ring.getMagicBonus() * 10;
        totalValue += price;
        System.out.printf("  [GoldAppraiser] Ring '%s': base=%d + magic*10=%d → %d gold%n",
                ring.getName(), ring.getValue(), ring.getMagicBonus() * 10, price);
    }
    @Override
    public void visit(Armor armor) {
        int price = armor.getValue() + armor.getDefenseBonus() * 4;
        totalValue += price;
        System.out.printf("  [GoldAppraiser] Armor '%s': base=%d + def*4=%d → %d gold%n",
                armor.getName(), armor.getValue(), armor.getDefenseBonus() * 4, price);
    }

    public int getTotalValue() { return totalValue; }
}
