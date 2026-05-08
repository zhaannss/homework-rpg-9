package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

/**
 * Visitor 3 — flags potentially dangerous or cursed artifacts.
 */
public class CurseDetector implements ArtifactVisitor {
    private int cursedCount = 0;

    @Override
    public void visit(Weapon weapon) {
        boolean cursed = weapon.getName().toLowerCase().contains("shadow")
                || weapon.getName().toLowerCase().contains("dark")
                || weapon.getAttackBonus() > 20;
        flag(weapon.getName(), "Weapon", cursed, "dark enchantment on blade");
    }
    @Override
    public void visit(Potion potion) {
        boolean cursed = potion.getHealing() < 0
                || potion.getName().toLowerCase().contains("poison");
        flag(potion.getName(), "Potion", cursed, "toxic residue detected");
    }
    @Override
    public void visit(Scroll scroll) {
        boolean cursed = scroll.getSpellName().toLowerCase().contains("death")
                || scroll.getSpellName().toLowerCase().contains("hex");
        flag(scroll.getName(), "Scroll", cursed, "forbidden incantation");
    }
    @Override
    public void visit(Ring ring) {
        boolean cursed = ring.getMagicBonus() < 0
                || ring.getName().toLowerCase().contains("void");
        flag(ring.getName(), "Ring", cursed, "void energy pulsing");
    }
    @Override
    public void visit(Armor armor) {
        boolean cursed = armor.getName().toLowerCase().contains("bone")
                || armor.getName().toLowerCase().contains("cursed");
        flag(armor.getName(), "Armor", cursed, "necrotic threads woven in");
    }

    private void flag(String name, String type, boolean cursed, String reason) {
        if (cursed) {
            cursedCount++;
            System.out.printf("  [CurseDetector] ⚠ CURSED %s '%s' — %s!%n", type, name, reason);
        } else {
            System.out.printf("  [CurseDetector] ✓ %s '%s' — clean%n", type, name);
        }
    }

    public int getCursedCount() { return cursedCount; }
}
