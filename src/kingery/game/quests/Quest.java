package kingery.game.quests;

import kingery.game.entities.npcs.NPC;

public abstract class Quest {

	protected int steps;
	protected int stepIndex = 0;
	protected NPC attatchedNPC;
	private String questTitle;

	public Quest(int steps, String title, NPC npc) {
		this.steps = steps;
		this.attatchedNPC = npc;
		questTitle = title;
	}

	public abstract void update();

	public void advanceQuest(int index) {

		if (index > stepIndex && index <= steps) {

			stepIndex = index;

		}

	}

	public boolean completed() {

		if (stepIndex == steps) {
			return true;
		}

		return false;

	}

	public String getQuestTitle() {
		return questTitle;
	}

}
