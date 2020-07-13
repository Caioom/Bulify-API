package com.bulefy.api.utils;

import java.util.Timer;
import java.util.TimerTask;

import com.bulefy.api.models.Data;
import com.bulefy.api.models.Lembrete;

public class AgendaUtils {
	public static void agendar(Lembrete lembrete) {
		final Timer timer = new Timer();
		
		for (Data data : lembrete.getDataHora()) {
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					System.out.println("Remédio agora!");
					timer.cancel();
				}
				
			}, data.getDataHora());
		}
	}
}
