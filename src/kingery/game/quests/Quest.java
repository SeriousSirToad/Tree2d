package kingery.game.quests;

import kingery.game.entities.npcs.NPC;

public abstract class Quest {
	
	protected int steps;
	protected int stepIndex = 0;
	protected String[] stepText;
	protected NPC attatchedNPC;
	
	public Quest(int steps, NPC npc) {
		this.steps = steps;
		this.attatchedNPC = npc;
		stepText = new String[steps];
	}
	
	public abstract void update();
	
	public void advanceQuest(int index) {
		
		if(index > stepIndex && index <= steps) {
			
			stepIndex = index;
			
		}
		
	}
	
	public boolean completed() {
		
		if(stepIndex == steps) {
			return true;
		}
		
		return false;
		
	}
	
}
