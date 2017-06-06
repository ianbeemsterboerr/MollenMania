package model;

import javafx.beans.property.SimpleStringProperty;

public class host_model {
		
		private SimpleStringProperty player_name;
		
		public host_model(String player_name){
			this.player_name = new SimpleStringProperty(player_name);
		}
	
		public String getPlayer_name() {
			return player_name.get();
		}
	
		public void setPlayer_name(String player_name) {
			this.player_name.set(player_name);
		}
}
