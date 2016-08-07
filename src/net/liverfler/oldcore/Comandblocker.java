package net.liverfler.oldcore;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Comandblocker {
	
	
	
	
	
	@EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)  {
		Player p = event.getPlayer();
		

        
     /*   
     String[] swearWords = {"all", "swear", "words", "in", "here"};
     // This replaces every punctuation symbol except @ by a space
     String message = event.getMessage().toLowerCase().replaceAll("@", "a").replaceAll("\\p{Punct}", " ");

     for(String word : swearWords){
       if(message.matches("(.* )?"+word+"( .*)?")) {
         // There has been a swear word in the message
         event.setMessage(event.getMessage().replaceAll(word, "*censored*"));
       }
     }
     */
     
        String[] tiltottparancsok = {"/?", "/pl", "/plugin", "/motdmanager", "'?'", "/help"};
        String[] swear = {"kurva", "fasz", "baszki", "bazdmeg", "ribanc"};
        String[] staffoknak = {"/ban"};
        // This replaces every punctuation symbol except @ by a space
        String message = event.getMessage().toLowerCase().replaceAll("@", "a").replaceAll("\\p{Punct}", " ");

        for(String word : tiltottparancsok){
          if(message.matches("(.* )?"+word+"( .*)?")) {
        	  if(!p.hasPermission("cucore.staff")){
        		  Utils.message(p, "Ezekhez a parancsokhoz csak a staffoknak van joguk.");
                  event.setCancelled(true);
        	  }
          }
        }
        
        for(String word : swear){
            if(message.matches("(.* )?"+word+"( .*)?")) {
            	if(!p.hasPermission("cucore.staff")){
            		Utils.message(p, "Próbálj meg kúltúráltan beszélni.");
                    event.setCancelled(true);
                    }
            	}
            }
        
        
        for(String word : staffoknak){
            if(message.matches("(.* )?"+word+"( .*)?")) {
            	if(p.hasPermission("cucore.staff")){
              Utils.message(p, "Légyszives használd azt a rohad cucore ban-t.");
              event.setCancelled(true);}
            }
          }

    }

}
