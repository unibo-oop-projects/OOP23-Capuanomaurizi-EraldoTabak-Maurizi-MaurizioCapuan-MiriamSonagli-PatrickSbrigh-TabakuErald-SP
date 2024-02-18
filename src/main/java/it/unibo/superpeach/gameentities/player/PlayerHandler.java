package it.unibo.superpeach.gameentities.player;

import java.awt.Graphics;
import java.util.Optional;

/**
 * This class manages the player.
 * @author Patrick Sbrighi
 */
public final class PlayerHandler {
    private Optional<Player> player;

    public PlayerHandler(){
        this.player = Optional.empty();
    }

    /**
     * This method allows to set the player.
     * @param p The player to set
     * @return Return 0 if the operation was successful -1 otherwise
     */
    public int takePlayer(final Player p) {
        if (!this.player.isPresent()) {
            this.player = Optional.of(p);
            return 0;
        }
        return -1;
    }

    /**
     * This method returns the player.
     * @return Return the player
     */
    public Player getPlayer() {
        return this.player.get();
    }

    /**
     * This method removes the player.
     * @return Return 0 if the operation was successful -1 otherwise
     */
    public int removePlayer() {
        if (this.player != null) {
            this.player = null;
            return 0;
        }
        return -1;
    }

    /**
     * This method calls the player tick.
     */
    public void tick() {
        this.player.get().tick();
    }

    /**
     * This method calls the player render.
     * @param g The graphics
     */
    public void render(final Graphics g) {
        if(this.player.isPresent()){
            this.player.get().render(g);
        }
    }
}
