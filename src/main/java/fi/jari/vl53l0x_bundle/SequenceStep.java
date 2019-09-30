/**
 *
 */
package fi.jari.vl53l0x_bundle;

/**
 * @author Nakki
 *
 */
public class SequenceStep {
	boolean tcc, dss, msrc, preRange, finalRange;

	public SequenceStep tcc(boolean tcc) {
		this.tcc = tcc;
		return this;
	}

	public SequenceStep dss(boolean dss) {
		this.dss = dss;
		return this;
	}

	public SequenceStep msrc(boolean msrc) {
		this.msrc = msrc;
		return this;
	}

	public SequenceStep preRange(boolean preRange) {
		this.preRange = preRange;
		return this;
	}

	public SequenceStep finalRange(boolean finalRange) {
		this.finalRange = finalRange;
		return this;
	}
}
