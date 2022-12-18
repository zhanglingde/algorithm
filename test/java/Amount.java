import java.math.BigDecimal;

/**
 * @author zhangling
 * @date 2022/11/24 2:55 PM
 */
public class Amount {

    private String id;
    private String name;
    private String unionCode;

    BigDecimal amount;


    public Amount() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnionCode() {
        return unionCode;
    }

    public void setUnionCode(String unionCode) {
        this.unionCode = unionCode;
    }

    public Amount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Amount(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", unionCode='" + unionCode + '\'' +
                ", amount=" + amount +
                '}';
    }
}
