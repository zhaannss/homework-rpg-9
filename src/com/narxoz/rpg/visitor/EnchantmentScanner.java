package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

/**
 * Visitor 2 — prints magical properties of each artifact.
 */
public class EnchantmentScanner implements ArtifactVisitor {
    @Override
    public void visit(Weapon weapon) {
        String level = weapon.getAttackBonus() >= 15 ? "LEGENDARY" :
                weapon.getAttackBonus() >= 8  ? "Rare"      : "Common";
        System.out.printf("  [EnchantmentScanner] Weapon '%s': ATK+%d → %s%n",
                weapon.getName(), weapon.getAttackBonus(), level);
    }
    @Override
    public void visit(Potion potion) {
        System.out.printf("  [EnchantmentScanner] Potion '%s': restores %d HP — Alchemical brew%n",
                potion.getName(), potion.getHealing());
    }
    @Override
    public void visit(Scroll scroll) {
        System.out.printf("  [EnchantmentScanner] Scroll '%s': spell [%s] — Arcane inscription detected%n",
                scroll.getName(), scroll.getSpellName());
    }
    @Override
    public void visit(Ring ring) {
        String aura = ring.getMagicBonus() >= 10 ? "Powerful aura" : "Faint aura";
        System.out.printf("  [EnchantmentScanner] Ring '%s': MAGIC+%d — %s%n",
                ring.getName(), ring.getMagicBonus(), aura);
    }
    @Override
    public void visit(Armor armor) {
        System.out.printf("  [EnchantmentScanner] Armor '%s': DEF+%d — Warded plating%n",
                armor.getName(), armor.getDefenseBonus());
    }
}
