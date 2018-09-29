package bl.domain.countries;

import bl.domain.DurationInWeeks;

import java.util.Date;

public class GovernmentForm {
    private final int id;
    private final String name;
    private int federalRuleTimeInWeeks;
    private int regionalRuleTimeInWeeks;
    private Date dataFederalElections;
    private Date dateRegionalElections;
    private DemocraticValue democraticValue;
    private HeadOfStateForm headOfStateForm;
    private StatePhilosophy statePhilosophy;

    public GovernmentForm() {
        this.id = 0;
        this.name = "";
    }

    public GovernmentForm(int id, String name, int federalRuleTimeInWeeks, int regionalRuleTimeInWeeks, Date dataFederalElections, Date dateRegionalElections) {
        this.id = id;
        this.name = name;
        this.federalRuleTimeInWeeks = federalRuleTimeInWeeks;
        this.regionalRuleTimeInWeeks = regionalRuleTimeInWeeks;
        this.dataFederalElections = dataFederalElections;
        this.dateRegionalElections = dateRegionalElections;
    }

    public GovernmentForm(int id, String name, DurationInWeeks durationInWeeksFederal, DurationInWeeks durationInWeeksRegional, Date dataFederalElections, Date dateRegionalElections, DemocraticValue democraticValue, HeadOfStateForm headOfStateForm, StatePhilosophy statePhilosophy) {
        this.id = id;
        this.name = name;
        this.federalRuleTimeInWeeks = durationInWeeksFederal.getDurationInWeeks();
        this.regionalRuleTimeInWeeks = durationInWeeksRegional.getDurationInWeeks();
        this.dataFederalElections = dataFederalElections;
        this.dateRegionalElections = dateRegionalElections;
        this.democraticValue = democraticValue;
        this.headOfStateForm = headOfStateForm;
        this.statePhilosophy = statePhilosophy;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFederalRuleTimeInWeeks() {
        return federalRuleTimeInWeeks;
    }

    public void setFederalRuleTimeInWeeks(int federalRuleTimeInWeeks) {
        this.federalRuleTimeInWeeks = federalRuleTimeInWeeks;
    }

    public int getRegionalRuleTimeInWeeks() {
        return regionalRuleTimeInWeeks;
    }

    public void setRegionalRuleTimeInWeeks(int regionalRuleTimeInWeeks) {
        this.regionalRuleTimeInWeeks = regionalRuleTimeInWeeks;
    }

    public Date getDataFederalElections() {
        return dataFederalElections;
    }

    public void setDataFederalElections(Date dataFederalElections) {
        this.dataFederalElections = dataFederalElections;
    }

    public DemocraticValue getDemocraticValue() {
        return democraticValue;
    }

    public void setDemocraticValue(DemocraticValue democraticValue) {
        this.democraticValue = democraticValue;
    }

    public HeadOfStateForm getHeadOfStateForm() {
        return headOfStateForm;
    }

    public void setHeadOfStateForm(HeadOfStateForm headOfStateForm) {
        this.headOfStateForm = headOfStateForm;
    }

    public StatePhilosophy getStatePhilosophy() {
        return statePhilosophy;
    }

    public void setStatePhilosophy(StatePhilosophy statePhilosophy) {
        this.statePhilosophy = statePhilosophy;
    }

    public Date getDateRegionalElections() {
        return dateRegionalElections;
    }

    public void setDateRegionalElections(Date dateRegionalElections) {
        this.dateRegionalElections = dateRegionalElections;
    }
}
