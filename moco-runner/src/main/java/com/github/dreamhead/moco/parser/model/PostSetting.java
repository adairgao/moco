package com.github.dreamhead.moco.parser.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.github.dreamhead.moco.Moco;
import com.github.dreamhead.moco.MocoEventAction;
import com.google.common.base.MoreObjects;

import static com.github.dreamhead.moco.Moco.post;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PostSetting {
    private TextContainer url;
    private TextContainer content;
    private Object json;

    public MocoEventAction createAction() {
        if (content != null) {
            return post(this.url.asResource(), content.asResource());
        }

        if (json != null) {
            return post(this.url.asResource(), Moco.json(json));
        }

        throw new IllegalArgumentException("content or json should be setup for post");
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("url", url)
                .add("content", content)
                .add("json", json)
                .toString();
    }
}
