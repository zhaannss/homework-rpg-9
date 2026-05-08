package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

/**
 * Visitor 4 — open/closed proof.
 * Added AFTER the initial 3 visitors, without modifying any file under artifact/.
 */
public class WeightCalculator implements ArtifactVisitor {
    private int totalWeight = 0;

    @Override
    public void visit(Weapon weapon) {
        totalWeight += weapon.getWeight();
        System.out.printf("  [WeightCalculator] Weapon '%s': %d kg (heavy)%n",
                weapon.getName(), weapon.getWeight());
    }
    @Override
    public void visit(Potion potion) {
        totalWeight += potion.getWeight();
        System.out.printf("  [WeightCalculator] Potion '%s': %d kg (light)%n",
                potion.getName(), potion.getWeight());
    }
    @Override
    public void visit(Scroll scroll) {
        totalWeight += scroll.getWeight();
        System.out.printf("  [WeightCalculator] Scroll '%s': %d kg (negligible)%n",
                scroll.getName(), scroll.getWeight());
    }
    @Override
    public void visit(Ring ring) {
        totalWeight += ring.getWeight();
        System.out.printf("  [WeightCalculator] Ring '%s': %d kg (tiny)%n",
                ring.getName(), ring.getWeight());
    }
    @Override
    public void visit(Armor armor) {
        totalWeight += armor.getWeight();
        System.out.printf("  [WeightCalculator] Armor '%s': %d kg (bulky)%n",
                armor.getName(), armor.getWeight());
    }

    public int getTotalWeight() { return totalWeight; }
}
