package com.fevertime.coinvillage.dto.savings;

import com.fevertime.coinvillage.domain.account.SavingsSetting;
import com.fevertime.coinvillage.domain.model.Term;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavingsSettingResponseDto {
    private Long settingsId;

    private Term term;

    private String week;

    private String day;

    private Long bill;

    public SavingsSettingResponseDto(SavingsSetting savingsSetting) {
        this.settingsId = savingsSetting.getSettingsId();
        this.term = savingsSetting.getTerm();
        this.week = savingsSetting.getWeek();
        this.day = savingsSetting.getDay();
        this.bill = savingsSetting.getBill();
    }
}
