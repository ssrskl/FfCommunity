package com.maoyan.ffcommunity.entity.vo.qearticlelike;

import com.maoyan.ffcommunity.entity.QeArticle;
import com.maoyan.ffcommunity.entity.QeArticleLike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QeArticleLikeQueryVO extends QeArticleLike {
    private QeArticle qeArticle;
}
