package game;

import javax.sound.sampled.*;
import java.util.HashMap;
import java.util.Map;

public class BGM {
    private static Clip currentBGM;
    private static final Map<String, String> levelBGM = new HashMap<>();

    // Map each level to its BGM file (call this once at game start)
    public static void setup() {
        // Update paths to match your actual file locations
        levelBGM.put("level1", "/sound/");
        levelBGM.put("level2", "/sound/Elysium in the Dream - Xenoblade Chronicles 2 OST [007].wav");
        levelBGM.put("boss", "/sound/data/bgm/boss.ogg");
    }

    // Play BGM for a specific level
    public static void playLevelBGM(String levelName) {
        try {
            // Stop previous BGM
            if (currentBGM != null && currentBGM.isRunning()) {
                currentBGM.stop();
            }

            // Load and play new BGM
            String path = levelBGM.get(levelName);
            if (path != null) {
                AudioInputStream audio = AudioSystem.getAudioInputStream(
                        BGM.class.getResource(path));
                currentBGM = AudioSystem.getClip();
                currentBGM.open(audio);
                currentBGM.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception e) {
            System.err.println("BGM Error: " + e.getMessage());
        }
    }

    // Stop all BGM
    public static void stop() {
        if (currentBGM != null) {
            currentBGM.stop();
        }
    }
}
