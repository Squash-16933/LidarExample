/**
 *
 */
package fi.jari.vl53l0x_bundle;

/**
 * @author Nakki
 *
 */
public class SequenceStepTimeouts {
	int msrcDssTccMicrosec, preRangeMicrosec, finalRangeMicorsec, finalRangeVcselPeriodPclks, preRangeMclks;

	public SequenceStepTimeouts msrcDssTccMicrosec(int msrcDssTccMicrosec) {
		this.msrcDssTccMicrosec = msrcDssTccMicrosec;
		return this;
	}

	public SequenceStepTimeouts preRangeMicrosec(int preRangeMicrosec) {
		this.preRangeMicrosec = preRangeMicrosec;
		return this;
	}

	public SequenceStepTimeouts finalRangeMicorsec(int finalRangeMicorsec) {
		this.finalRangeMicorsec = finalRangeMicorsec;
		return this;
	}

	public SequenceStepTimeouts finalRangeVcselPeriodPclks(int finalRangeVcselPeriodPclks) {
		this.finalRangeVcselPeriodPclks = finalRangeVcselPeriodPclks;
		return this;
	}

	public SequenceStepTimeouts preRangeMclks(int preRangeMclks) {
		this.preRangeMclks = preRangeMclks;
		return this;
	}
}
