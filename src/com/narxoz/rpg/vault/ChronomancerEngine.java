package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import com.narxoz.rpg.visitor.*;

import java.util.List;

/**
 * Orchestrates the Chronomancer's Vault demo run.
 * Wires together Visitor (appraisal) and Memento (snapshot/rewind).
 */
public class ChronomancerEngine {

    public VaultRunResult runVault(List<Hero> party) {
        int artifactsAppraised = 0;
        int mementosCreated   = 0;
        int restoredCount     = 0;

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     THE CHRONOMANCER'S VAULT             ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // ── Build mixed inventory (≥ 5 artifacts) ────────────────────────
        Inventory vault = new Inventory();
        vault.addArtifact(new Weapon("Shadow Blade",     120, 8, 18));
        vault.addArtifact(new Potion("Grand Healing Vial", 60, 1, 80));
        vault.addArtifact(new Scroll("Void Scroll",      90, 1, "Hex of Doom"));
        vault.addArtifact(new Ring("Ring of Power",      200, 0, 12));
        vault.addArtifact(new Armor("Bone Plate",        150, 15, 20));
        vault.addArtifact(new Weapon("Iron Sword",        50, 5, 8));

        artifactsAppraised = vault.size();
        System.out.println("\n[VAULT] " + artifactsAppraised + " artifacts discovered.\n");

        // ── Visitor 1: GoldAppraiser ──────────────────────────────────────
        System.out.println("=== APPRAISAL: Gold Value ===");
        GoldAppraiser goldAppraiser = new GoldAppraiser();
        vault.accept(goldAppraiser);   // double dispatch via Inventory.accept()
        System.out.println("  Total vault value: " + goldAppraiser.getTotalValue() + " gold\n");

        // ── Visitor 2: EnchantmentScanner ─────────────────────────────────
        System.out.println("=== APPRAISAL: Enchantment Scan ===");
        vault.accept(new EnchantmentScanner());
        System.out.println();

        // ── Visitor 3: CurseDetector ──────────────────────────────────────
        System.out.println("=== APPRAISAL: Curse Detection ===");
        CurseDetector curseDetector = new CurseDetector();
        vault.accept(curseDetector);
        System.out.println("  Cursed artifacts found: " + curseDetector.getCursedCount() + "\n");

        // ── Visitor 4: WeightCalculator (open/closed proof) ───────────────
        System.out.println("=== APPRAISAL: Weight Calculation (4th visitor — open/closed proof) ===");
        WeightCalculator weightCalc = new WeightCalculator();
        vault.accept(weightCalc);
        System.out.println("  Total carry weight: " + weightCalc.getTotalWeight() + " kg\n");

        // ── Memento: snapshot heroes before vault trap ─────────────────────
        System.out.println("=== MEMENTO: Saving hero snapshots before vault trap ===");
        Caretaker caretaker = new Caretaker();

        for (Hero hero : party) {
            HeroMemento snapshot = hero.createMemento();
            caretaker.save(snapshot);
            mementosCreated++;
            System.out.println("  [SNAPSHOT] " + hero.getName()
                    + " saved — HP=" + hero.getHp()
                    + " mana=" + hero.getMana()
                    + " gold=" + hero.getGold()
                    + " items=" + hero.getInventory().size());
        }
        System.out.println("  Caretaker holds " + caretaker.size() + " snapshot(s).\n");

        // ── Vault trap event — damages heroes and drains mana/gold ────────
        System.out.println("=== VAULT EVENT: Time Crystal Trap triggers! ===");
        for (Hero hero : party) {
            hero.takeDamage(50);
            hero.spendMana(30);
            hero.spendGold(100);
            hero.setInventory(new Inventory()); // inventory lost
            System.out.println("  [TRAP] " + hero.getName()
                    + " — HP=" + hero.getHp()
                    + " mana=" + hero.getMana()
                    + " gold=" + hero.getGold()
                    + " items=" + hero.getInventory().size());
        }
        System.out.println();

        // ── Memento: restore heroes from snapshots ─────────────────────────
        System.out.println("=== MEMENTO: Rewinding heroes to saved state ===");
        for (int i = party.size() - 1; i >= 0; i--) {
            Hero hero = party.get(i);
            HeroMemento snapshot = caretaker.undo();
            if (snapshot != null) {
                hero.restoreFromMemento(snapshot);
                restoredCount++;
                System.out.println("  [REWIND] " + hero.getName()
                        + " restored — HP=" + hero.getHp()
                        + " mana=" + hero.getMana()
                        + " gold=" + hero.getGold()
                        + " items=" + hero.getInventory().size());
            }
        }
        System.out.println("  Caretaker remaining snapshots: " + caretaker.size() + "\n");

        return new VaultRunResult(artifactsAppraised, mementosCreated, restoredCount);
    }
}
