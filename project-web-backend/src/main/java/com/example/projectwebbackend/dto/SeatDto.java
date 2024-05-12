package com.example.projectwebbackend.dto;

import com.example.projectwebbackend.entity.Coach;
import com.example.projectwebbackend.entity.SeatLocation;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.example.projectwebbackend.entity.Seat}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeatDto implements Serializable {
    private Long price;
    private Coach coach;
    private String type;
    private SeatLocation seatLocation;

}