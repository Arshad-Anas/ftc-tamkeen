package org.firstinspires.ftc.teamcode.roadrunner;

//import android.support.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Arrays;
import java.util.List;

/*
 * Sample tracking wheel localizer implementation assuming the standard configuration:
 *
 *    /--------------\
 *    |     ____     |
 *    |     ----     |
 *    | ||        || |
 *    | ||        || |
 *    |              |
 *    |              |
 *    \--------------/
 *
 */
@Config
public class StandardTrackingWheelLocalizer extends ThreeTrackingWheelLocalizer {
    public static double TICKS_PER_REV = 2400;
    //public static double WHEEL_RADIUS = 1.1811; // in
    public static double WHEEL_RADIUS = 1.1811; // in
    public static double GEAR_RATIO = 1.0; // output (wheel) speed / input (encoder) speed

    public static double LATERAL_DISTANCE = 14.15; // in; distance between the left and right wheels
    public static double FORWARD_OFFSET = -6.5; // in; offset of the lateral wheel

    private DcMotor leftEncoder, rightEncoder, frontEncoder;

    private int leftEncoderMod = 1;
    private int rightEncoderMod = 1;
    private int frontEncoderMod = 1;

    public StandardTrackingWheelLocalizer(HardwareMap hardwareMap) {
        super(Arrays.asList(
                new Pose2d(.5, LATERAL_DISTANCE / 2, 0), // left
                new Pose2d(.5, -LATERAL_DISTANCE / 2, 0), // right
                new Pose2d(FORWARD_OFFSET, 0, Math.toRadians(90)) // front
        ));

        leftEncoder = hardwareMap.dcMotor.get("right back");
        rightEncoder = hardwareMap.dcMotor.get("right front");
        frontEncoder = hardwareMap.dcMotor.get("left back");

    }

    public double getLeftEncoder() {
        return encoderTicksToInches(leftEncoder.getCurrentPosition()*leftEncoderMod);
    }
    public double getRightEncoder() {
        return encoderTicksToInches(rightEncoder.getCurrentPosition()*rightEncoderMod);
    }
    public double getFrontEncoder() {
        return encoderTicksToInches(frontEncoder.getCurrentPosition()*frontEncoderMod);
    }

    public static double encoderTicksToInches(int ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        return Arrays.asList(
                encoderTicksToInches(leftEncoder.getCurrentPosition()*leftEncoderMod),
                encoderTicksToInches(rightEncoder.getCurrentPosition()*rightEncoderMod),
                encoderTicksToInches(frontEncoder.getCurrentPosition()*frontEncoderMod)
        );
    }
}