package fr.skytasul.glowingentities;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

/**
 * Main plugin class for GlowingEntities API
 *
 * @author SkytAsul
 * @version 1.4.6
 */
public class GlowingEntitiesPlugin extends JavaPlugin {

    private static GlowingEntitiesPlugin instance;
    private GlowingEntities glowingEntities;
    private GlowingBlocks glowingBlocks;

    @Override
    public void onEnable() {
        instance = this;

        try {
            // Initialize the core GlowingEntities API
            glowingEntities = new GlowingEntities(this);
            getLogger().info("GlowingEntities API initialized successfully!");

            // Try to initialize GlowingBlocks (Paper only)
            if (isPaperServer()) {
                try {
                    glowingBlocks = new GlowingBlocks(this);
                    getLogger().info("GlowingBlocks API initialized successfully! (Paper detected)");
                } catch (UnsupportedOperationException e) {
                    getLogger().warning("GlowingBlocks API is only supported on Paper servers: " + e.getMessage());
                } catch (Exception e) {
                    getLogger().log(Level.SEVERE, "Failed to initialize GlowingBlocks API", e);
                }
            } else {
                getLogger().info("Paper server not detected. GlowingBlocks API will not be available.");
            }

            getLogger().info("GlowingEntities plugin v" + getDescription().getVersion() + " enabled!");

        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Failed to initialize GlowingEntities API! Plugin will be disabled.", e);
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        try {
            // Disable GlowingBlocks first (depends on GlowingEntities)
            if (glowingBlocks != null) {
                glowingBlocks.disable();
                glowingBlocks = null;
                getLogger().info("GlowingBlocks API disabled.");
            }

            // Disable core GlowingEntities
            if (glowingEntities != null) {
                glowingEntities.disable();
                glowingEntities = null;
                getLogger().info("GlowingEntities API disabled.");
            }

        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Error occurred while disabling GlowingEntities", e);
        }

        instance = null;
        getLogger().info("GlowingEntities plugin disabled!");
    }

    /**
     * Get the plugin instance
     *
     * @return the plugin instance, or null if not enabled
     */
    public static GlowingEntitiesPlugin getInstance() {
        return instance;
    }

    /**
     * Get the GlowingEntities API instance
     *
     * @return the GlowingEntities API, or null if not available
     */
    public GlowingEntities getGlowingEntities() {
        return glowingEntities;
    }

    /**
     * Get the GlowingBlocks API instance (Paper only)
     *
     * @return the GlowingBlocks API, or null if not available
     */
    public GlowingBlocks getGlowingBlocks() {
        return glowingBlocks;
    }

    /**
     * Check if GlowingEntities API is available
     *
     * @return true if the API is ready to use
     */
    public boolean isGlowingEntitiesAvailable() {
        return glowingEntities != null && glowingEntities.enabled;
    }

    /**
     * Check if GlowingBlocks API is available
     *
     * @return true if the API is ready to use (Paper only)
     */
    public boolean isGlowingBlocksAvailable() {
        return glowingBlocks != null && glowingBlocks.enabled;
    }

    /**
     * Detect if running on Paper server
     */
    private boolean isPaperServer() {
        try {
            Class.forName("io.papermc.paper.event.packet.PlayerChunkLoadEvent");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}