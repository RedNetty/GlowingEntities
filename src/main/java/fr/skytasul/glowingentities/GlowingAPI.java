package fr.skytasul.glowingentities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Static API wrapper for GlowingEntities plugin
 *
 * This class provides easy static access to the GlowingEntities API for other plugins.
 *
 * @author SkytAsul
 * @version 1.4.6
 */
public class GlowingAPI {

    /**
     * Get the GlowingEntities plugin instance
     *
     * @return the plugin instance, or null if not loaded
     */
    @Nullable
    public static GlowingEntitiesPlugin getPlugin() {
        return GlowingEntitiesPlugin.getInstance();
    }

    /**
     * Get the GlowingEntities API instance
     *
     * @return the API instance, or null if not available
     * @throws IllegalStateException if the plugin is not loaded or API is not available
     */
    @NotNull
    public static GlowingEntities getGlowingEntities() throws IllegalStateException {
        GlowingEntitiesPlugin plugin = getPlugin();
        if (plugin == null) {
            throw new IllegalStateException("GlowingEntities plugin is not loaded!");
        }

        GlowingEntities api = plugin.getGlowingEntities();
        if (api == null) {
            throw new IllegalStateException("GlowingEntities API is not available!");
        }

        return api;
    }

    /**
     * Get the GlowingBlocks API instance (Paper only)
     *
     * @return the API instance, or null if not available
     * @throws IllegalStateException if the plugin is not loaded or API is not available
     * @throws UnsupportedOperationException if not running on Paper
     */
    @NotNull
    public static GlowingBlocks getGlowingBlocks() throws IllegalStateException, UnsupportedOperationException {
        GlowingEntitiesPlugin plugin = getPlugin();
        if (plugin == null) {
            throw new IllegalStateException("GlowingEntities plugin is not loaded!");
        }

        GlowingBlocks api = plugin.getGlowingBlocks();
        if (api == null) {
            throw new UnsupportedOperationException("GlowingBlocks API is not available! (Requires Paper server)");
        }

        return api;
    }

    /**
     * Check if GlowingEntities API is available
     *
     * @return true if the API is ready to use
     */
    public static boolean isGlowingEntitiesAvailable() {
        GlowingEntitiesPlugin plugin = getPlugin();
        return plugin != null && plugin.isGlowingEntitiesAvailable();
    }

    /**
     * Check if GlowingBlocks API is available
     *
     * @return true if the API is ready to use (Paper only)
     */
    public static boolean isGlowingBlocksAvailable() {
        GlowingEntitiesPlugin plugin = getPlugin();
        return plugin != null && plugin.isGlowingBlocksAvailable();
    }

    // === CONVENIENCE METHODS FOR ENTITIES ===

    /**
     * Make an entity glow with its default team color
     *
     * @param entity the entity to make glow
     * @param receiver the player who will see the glowing effect
     * @throws ReflectiveOperationException if packet manipulation fails
     * @throws IllegalStateException if API is not available
     */
    public static void setGlowing(@NotNull Entity entity, @NotNull Player receiver)
            throws ReflectiveOperationException, IllegalStateException {
        getGlowingEntities().setGlowing(entity, receiver);
    }

    /**
     * Make an entity glow with a specific color
     *
     * @param entity the entity to make glow
     * @param receiver the player who will see the glowing effect
     * @param color the color of the glowing effect
     * @throws ReflectiveOperationException if packet manipulation fails
     * @throws IllegalStateException if API is not available
     */
    public static void setGlowing(@NotNull Entity entity, @NotNull Player receiver, @Nullable ChatColor color)
            throws ReflectiveOperationException, IllegalStateException {
        getGlowingEntities().setGlowing(entity, receiver, color);
    }

    /**
     * Remove glowing effect from an entity
     *
     * @param entity the entity to remove glowing from
     * @param receiver the player who will no longer see the glowing effect
     * @throws ReflectiveOperationException if packet manipulation fails
     * @throws IllegalStateException if API is not available
     */
    public static void unsetGlowing(@NotNull Entity entity, @NotNull Player receiver)
            throws ReflectiveOperationException, IllegalStateException {
        getGlowingEntities().unsetGlowing(entity, receiver);
    }

    // === CONVENIENCE METHODS FOR BLOCKS (PAPER ONLY) ===

    /**
     * Make a block glow with a specific color (Paper only)
     *
     * @param block the block to make glow
     * @param receiver the player who will see the glowing effect
     * @param color the color of the glowing effect
     * @throws ReflectiveOperationException if packet manipulation fails
     * @throws IllegalStateException if API is not available
     * @throws UnsupportedOperationException if not running on Paper
     */
    public static void setGlowing(@NotNull Block block, @NotNull Player receiver, @NotNull ChatColor color)
            throws ReflectiveOperationException, IllegalStateException, UnsupportedOperationException {
        getGlowingBlocks().setGlowing(block, receiver, color);
    }

    /**
     * Make a block at a location glow with a specific color (Paper only)
     *
     * @param location the location of the block to make glow
     * @param receiver the player who will see the glowing effect
     * @param color the color of the glowing effect
     * @throws ReflectiveOperationException if packet manipulation fails
     * @throws IllegalStateException if API is not available
     * @throws UnsupportedOperationException if not running on Paper
     */
    public static void setGlowing(@NotNull Location location, @NotNull Player receiver, @NotNull ChatColor color)
            throws ReflectiveOperationException, IllegalStateException, UnsupportedOperationException {
        getGlowingBlocks().setGlowing(location, receiver, color);
    }

    /**
     * Remove glowing effect from a block (Paper only)
     *
     * @param block the block to remove glowing from
     * @param receiver the player who will no longer see the glowing effect
     * @throws ReflectiveOperationException if packet manipulation fails
     * @throws IllegalStateException if API is not available
     * @throws UnsupportedOperationException if not running on Paper
     */
    public static void unsetGlowing(@NotNull Block block, @NotNull Player receiver)
            throws ReflectiveOperationException, IllegalStateException, UnsupportedOperationException {
        getGlowingBlocks().unsetGlowing(block, receiver);
    }

    /**
     * Remove glowing effect from a block at a location (Paper only)
     *
     * @param location the location of the block to remove glowing from
     * @param receiver the player who will no longer see the glowing effect
     * @throws ReflectiveOperationException if packet manipulation fails
     * @throws IllegalStateException if API is not available
     * @throws UnsupportedOperationException if not running on Paper
     */
    public static void unsetGlowing(@NotNull Location location, @NotNull Player receiver)
            throws ReflectiveOperationException, IllegalStateException, UnsupportedOperationException {
        getGlowingBlocks().unsetGlowing(location, receiver);
    }

    // === DEPENDENCY CHECK ===

    /**
     * Check if GlowingEntities plugin is a dependency and throw exception if not available
     *
     * This method is useful for plugins that require GlowingEntities to function.
     * Call this in your plugin's onEnable() method.
     *
     * @param plugin your plugin instance (for logging purposes)
     * @throws IllegalStateException if GlowingEntities is not available
     */
    public static void requireGlowingEntities(@NotNull Plugin plugin) throws IllegalStateException {
        if (!isGlowingEntitiesAvailable()) {
            plugin.getLogger().severe("GlowingEntities plugin is required but not available!");
            throw new IllegalStateException("GlowingEntities plugin is required but not available!");
        }
        plugin.getLogger().info("GlowingEntities API successfully loaded!");
    }

    /**
     * Check if GlowingBlocks API is available and log appropriate message
     *
     * @param plugin your plugin instance (for logging purposes)
     * @return true if GlowingBlocks is available
     */
    public static boolean checkGlowingBlocks(@NotNull Plugin plugin) {
        if (isGlowingBlocksAvailable()) {
            plugin.getLogger().info("GlowingBlocks API is available!");
            return true;
        } else {
            plugin.getLogger().warning("GlowingBlocks API is not available (requires Paper server)");
            return false;
        }
    }
}