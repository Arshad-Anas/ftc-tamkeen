package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.centerstage.Robot;

@TeleOp(name="Dual Servo Control", group="23925 TeleOp")
public class TeleOpFIXServo extends LinearOpMode {
    MultipleTelemetry mTelemetry;
    GamepadEx gamepadEx1, gamepadEx2;
    Robot robot;
    boolean servoPositionIsZero = false;
    DcMotor hangerMotor;
    Servo clawServo1, clawServo2, upDownServo1, upDownServo2;

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize multiple telemetry outputs:
        mTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        robot = new Robot(hardwareMap);

        hangerMotor = hardwareMap.dcMotor.get("hangerMotor");
        clawServo1 = hardwareMap.servo.get("clawServo1");
        clawServo2 = hardwareMap.servo.get("clawServo2");
        upDownServo1 = hardwareMap.servo.get("upDownServo1");
        upDownServo2 = hardwareMap.servo.get("upDownServo2");

//        clawServo1.setPosition(0.0);
//        clawServo2.setPosition(0.0);
        upDownServo1.setPosition(0.0);
        upDownServo2.setPosition(0.0);

        robot.readSensors();
        // Read sensors + gamepads:
        gamepadEx1.readButtons();
        gamepadEx2.readButtons();

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad2.a) {
                upDownServo1.setPosition(0.0);
                upDownServo2.setPosition(0.0);
                telemetry.addData("motor", upDownServo1.getPosition());

            }

            if (gamepad2.b) {
                upDownServo1.setPosition(.5);
                upDownServo2.setPosition(.5);
                telemetry.addData("motor", upDownServo2.getPosition());

            }
        }
    }
}
