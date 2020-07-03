package entity;

import lombok.Data;

import java.util.List;

/**
 *  * 分页结果类，可作为结果中的data数据
 * @param <T>
 */
@Data
public class PageResult<T> {
    private Long count;
    private List<T> rows;

    public PageResult(Long count, List<T> rows) {
        this.count = count;
        this.rows = rows;
    }
}
