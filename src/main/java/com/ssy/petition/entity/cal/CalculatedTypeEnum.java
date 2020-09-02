package com.ssy.petition.entity.cal;

/**
 * @author: jcm
 * @date: 2020/08/26
 */
public enum CalculatedTypeEnum {

    /**
     *
     */
    ADMIN_CYCLE_ACCEPT("adminCycleAccepts", CycleAccept.class),

    PARTY_CYCLE_ACCEPT("partyCycleAccepts", CycleAccept.class),

    ADMIN_FIRST_ACCEPT("adminFirstAccepts", FirstAccept.class),

    PARTY_FIRST_ACCEPT("partyFirstAccepts", FirstAccept.class),

    ADMIN_FIRST_CONTACT("adminFirstContacts", FirstContact.class),

    PARTY_FIRST_CONTACT("partyFirstContacts", FirstContact.class),

    ADMIN_REPEAT_PETITION("adminRepeatPetitions", RepeatPetition.class),

    PARTY_REPEAT_PETITION("partyRepeatPetitions", RepeatPetition.class),

    ADMIN_PUBLIC_REPLY("adminPublicReplies", PublicReply.class),

    PARTY_PUBLIC_REPLY("partyPublicReplies", PublicReply.class),

    ADMIN_SATISFACTION("adminSatisfactions", Satisfaction.class),

    PARTY_SATISFACTION("partySatisfactions", Satisfaction.class),
    ;

    public static CalculatedTypeEnum getByName(String name) {
        for (CalculatedTypeEnum calculatedTypeEnum : CalculatedTypeEnum.values()) {
            if (calculatedTypeEnum.getName().equals(name)) {
                return calculatedTypeEnum;
            }
        }
        return null;
    }

    private String name;

    private Class<? extends ParamsEntity> cls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends ParamsEntity> getCls() {
        return cls;
    }

    public void setCls(Class<? extends ParamsEntity> cls) {
        this.cls = cls;
    }

    CalculatedTypeEnum(String name, Class<? extends ParamsEntity> cls) {
        this.name = name;
        this.cls = cls;
    }
}
