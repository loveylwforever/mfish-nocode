package cn.com.mfish.common.oauth.scope;

import cn.com.mfish.common.core.utils.AuthInfoUtils;
import cn.com.mfish.common.oauth.common.DataScopeUtils;
import cn.com.mfish.common.core.utils.StringUtils;

/**
 * @description: 租户数据处理
 * @author: mfish
 * @date: 2024/4/26
 */
public class TenantDataScopeHandle implements DataScopeHandle {
    private static final String DEFAULT_FIELD = "tenant_id";

    @Override
    public String buildCondition(String fieldName, String[] values, String[] excludes) {
        fieldName = StringUtils.isEmpty(fieldName) ? DEFAULT_FIELD : fieldName;
        if (values == null || values.length == 0) {
            //未传值时使用当前租户id
            values = new String[]{AuthInfoUtils.getCurrentTenantId()};
        } else {
            // 如果值为空置为当前租户id
            for (int i = 0; i < values.length; i++) {
                if (StringUtils.isEmpty(values[i])) {
                    values[i] = AuthInfoUtils.getCurrentTenantId();
                }
            }
        }
        return DataScopeUtils.buildCondition(fieldName, values, excludes);
    }
}
