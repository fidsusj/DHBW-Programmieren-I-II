package de.dhbwka.java.exercise.uebungsklausuren.Totospiel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Totalisator {

	private String name;
	private List<Paarung> paarungen;
	private List<Tipper> tipper;
	private Map<Tipper,Boolean> checklist;
	
	public Totalisator(String name) {
		this.name = name;
		this.paarungen = new ArrayList<>();
		this.tipper = new ArrayList<>();
		this.checklist = new HashMap<Tipper, Boolean>();
	}
	
	public void addPaarung(Paarung p) {
		if(!this.paarungen.contains(p))
			this.paarungen.add(p);
	}
	
	public void removePaarung(Paarung p) {
		this.paarungen.remove(p);
	}
	
	public void addTipper(Tipper t) {
		if(!this.tipper.contains(t)) {
			this.tipper.add(t);
			this.checklist.put(t, false);
		}
	}
	
	public void removeTipper(Tipper t) {
		this.tipper.remove(t);
		this.checklist.remove(t);
	}

	public String getName() {
		return name;
	}

	public List<Paarung> getPaarungen() {
		return paarungen;
	}

	public void notifyCompleteTipp(Tipper tipper) {
		this.checklist.put(tipper, true);
		if(this.tippsComplete()) {
			for (Paarung paarung : paarungen) {
				paarung.spielen();
			}
			this.evaluateResult();
		}
	}

	private boolean tippsComplete() {
		boolean completed = true;
		for (Iterator<Entry<Tipper, Boolean>> iterator = checklist.entrySet().iterator(); iterator.hasNext();) {
			Entry<Tipper, Boolean> entry = (Entry<Tipper, Boolean>) iterator.next();
			if(!entry.getValue()) {
				completed = false;
				break;
			}
		}
		return completed;
	}
	
	private void evaluateResult() {
		List<Integer> results = new ArrayList<>();
		for (Paarung paarung : this.paarungen) {
			int toreHeim = paarung.getToreHeim();
			int toreGast = paarung.getToreGast();
			int result = 0;
			if(toreHeim == toreGast) {
				result = 0;
			} else if ( toreHeim > toreGast) {
				result = 1;
			} else {
				result = 2;
			}
			results.add(result);
		}
		
		for (Tipper tipper : this.tipper) {
			List<Tipp> tipps = tipper.getTipps();
			int sum = 0;
			for (int i = 0; i < results.size(); i++) {
				if(tipps.get(i).getErgebnisTipp() == results.get(i)) {
					sum++;
				}
			}
			tipper.receiveResult(sum);
		}
	}
	
	public void sortPaarungen() {
		Collections.sort(this.paarungen, new PaarungComparator());
	}
	
}