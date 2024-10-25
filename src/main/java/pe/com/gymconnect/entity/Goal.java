package pe.com.gymconnect.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int code;

    @ManyToOne
    @JoinColumn(name = "gym_id", nullable = false)
    private Gym gym;

    // @Column(name = "gym_id", nullable = false)
    // private Long gymId;

    // public void setGymById(Long gymId) {
    // this.gymId = gymId;
    // if (gymId != null) {
    // Gym gym = new Gym();
    // gym.setId(gymId);
    // this.gym = gym;
    // } else
    // this.gym = null;
    // }

}
