package com.narxoz.rpg;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        // ── 1. Create 2 heroes with different starting states ─────────────
        Inventory aragornInventory = new Inventory();
        aragornInventory.addArtifact(new Weapon("Anduril", 300, 6, 20));
        aragornInventory.addArtifact(new Potion("Health Potion", 40, 1, 50));

        Inventory gandalfInventory = new Inventory();
        gandalfInventory.addArtifact(new Scroll("Gandalf's Scroll", 150, 1, "Fireball"));
        gandalfInventory.addArtifact(new Ring("Narya", 500, 0, 15));

        Hero aragorn = new Hero("Aragorn", 200, 80,  40, 15, 300, aragornInventory);
        Hero gandalf = new Hero("Gandalf", 140, 150, 30, 10, 500, gandalfInventory);

        List<Hero> party = List.of(aragorn, gandalf);

        System.out.println("\n=== INITIAL PARTY ===");
        party.forEach(System.out::println);

        // ── 2. Run the vault engine ───────────────────────────────────────
        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(party);

        // ── 3. Print final state and VaultRunResult ───────────────────────
        System.out.println("=== FINAL PARTY STATE ===");
        party.forEach(System.out::println);

        System.out.println("\n=== VAULT RUN RESULT ===");
        System.out.println("  Artifacts appraised: " + result.getArtifactsAppraised());
        System.out.println("  Mementos created:    " + result.getMementosCreated());
        System.out.println("  Heroes restored:     " + result.getRestoredCount());
        System.out.println("  " + result);
    }
}
