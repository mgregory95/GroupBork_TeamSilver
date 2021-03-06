/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;
class MovementCommand extends Command {

    private String dir;
                       

    MovementCommand(String dir) {
        this.dir = dir;
    }

    public String execute() {
        GameState gs = GameState.instance();
        gs.updateNumMoves(1);
        gs.updateHunger(-1);
        Room currentRoom = gs.getAdventurersCurrentRoom();
        Room nextRoom = currentRoom.leaveBy(dir);
        if (nextRoom != null) {  // could try/catch here.
            if(!nextRoom.isLocked()){
                gs.setAdventurersCurrentRoom(nextRoom);
                return "\n" + nextRoom.describe() + "\n";
            } else {
                return "This room is locked! Answer this riddle to unlock it.\n" 
                        + nextRoom.getRiddle() + "\n**To answer a riddle, begin the line with "
                        + "'answer: ' and type in your answer**\n";
            }
        } else {
            return "You can't go " + dir + ".\n";
        }
    
    }
}
