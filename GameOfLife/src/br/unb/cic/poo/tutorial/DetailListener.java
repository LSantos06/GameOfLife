package br.unb.cic.poo.tutorial;

import java.util.EventListener;

public interface DetailListener extends EventListener{
	public void detailEventOccurred(DetailEvent event);
}
