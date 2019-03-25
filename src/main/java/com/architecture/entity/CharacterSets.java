package com.architecture.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dirk
 * @since 2019-03-25
 */
public class CharacterSets extends Model<CharacterSets> {

    private static final long serialVersionUID = 1L;

    private String characterSetName;
    private String defaultCollateName;
    private String description;
    private Long maxlen;


    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public String getDefaultCollateName() {
        return defaultCollateName;
    }

    public void setDefaultCollateName(String defaultCollateName) {
        this.defaultCollateName = defaultCollateName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMaxlen() {
        return maxlen;
    }

    public void setMaxlen(Long maxlen) {
        this.maxlen = maxlen;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "CharacterSets{" +
        "characterSetName=" + characterSetName +
        ", defaultCollateName=" + defaultCollateName +
        ", description=" + description +
        ", maxlen=" + maxlen +
        "}";
    }
}
