package org.firstinspires.ftc.teamcode.opmodes;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.centerstage.Robot;

@TeleOp(group = "23925 TeleOp")
public class mainTeleOpMeacunum extends LinearOpMode {

    MultipleTelemetry mTelemetry;
    GamepadEx gamepadEx1, gamepadEx2;
    Robot robot;
    DcMotor hangerMotor, submersibleMotor, specimenMotor;
    Servo clawServo1, clawServo2, upDownServo1, upDownServo2, clawSpecimenServo1, clawSpecimenServo2;

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize multiple telemetry outputs:
        mTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        robot = new Robot(hardwareMap);

        hangerMotor = hardwareMap.dcMotor.get("hangerMotor");
        specimenMotor = hardwareMap.dcMotor.get("specimenMotor");
        submersibleMotor = hardwareMap.dcMotor.get("submersibleMotor");
        clawServo1 = hardwareMap.servo.get("clawServo1");
        clawServo2 = hardwareMap.servo.get("clawServo2");
        clawSpecimenServo1 = hardwareMap.servo.get("clawSpecimenServo1");
        clawSpecimenServo2 = hardwareMap.servo.get("clawSpecimenServo2");
        upDownServo1 = hardwareMap.servo.get("upDownServo1");
        //upDownServo2 = hardwareMap.servo.get("upDownServo2");

        robot.readSensors();
        // Read sensors + gamepads:
        gamepadEx1.readButtons();
        gamepadEx2.readButtons();

        waitForStart();

        // Control loop:
        while (opModeIsActive()) {

            // for the submersible claw to go up so the sample can leave the zone
            if (gamepad2.dpad_down) {
                upDownServo1.setDirection(Servo.Direction.REVERSE);
                upDownServo1.setPosition(0.0);
                //upDownServo2.setDirection(Servo.Direction.FORWARD);
                //upDownServo2.setPosition(0.0);
            }

            if (gamepad2.dpad_up) {
                upDownServo1.setDirection(Servo.Direction.REVERSE);
                upDownServo1.setPosition(0.5);
                //upDownServo2.setDirection(Servo.Direction.FORWARD);
                //upDownServo2.setPosition(0.5);
            }

            // For the claw to open and close grab things in the submersible
            if (gamepad2.y) {
                clawServo1.setDirection(Servo.Direction.FORWARD);
                clawServo1.setPosition(0.0);
                clawServo2.setDirection(Servo.Direction.REVERSE);
                clawServo2.setPosition(0.0);
            }

            if (gamepad2.x) {
                clawServo1.setDirection(Servo.Direction.REVERSE);
                clawServo1.setPosition(.5);
                clawServo2.setDirection(Servo.Direction.FORWARD);
                clawServo2.setPosition(.5);
            }

            //for the claw specimen
            if (gamepad2.a) {
                clawSpecimenServo1.setDirection(Servo.Direction.FORWARD);
                clawSpecimenServo1.setPosition(0.0);
                clawSpecimenServo2.setDirection(Servo.Direction.REVERSE);
                clawSpecimenServo2.setPosition(0.0);
            }

            if (gamepad2.b) {
                clawSpecimenServo1.setDirection(Servo.Direction.REVERSE);
                clawSpecimenServo1.setPosition(.5);
                clawSpecimenServo2.setDirection(Servo.Direction.FORWARD);
                clawSpecimenServo2.setPosition(.5);
            }

            //For the hanger
            if (gamepad2.atRest()) {
                hangerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }

            telemetry.addData("motor", upDownServo1.getPosition());

            specimenMotor.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
            submersibleMotor.setPower(gamepad2.right_stick_y);
            hangerMotor.setPower(gamepad1.left_trigger - gamepad1.right_trigger);

            // Field-centric drive dt with control stick inputs:
            robot.drivetrain.run(
                    gamepadEx1.getLeftX(),
                    gamepadEx1.getLeftY(),
                    gamepadEx1.getRightX()
            );

            mTelemetry.update();
            telemetry.update();
            robot.interrupt();
        }
    }

    public void setPosition(double position) {
        //doorServo1.setPosition(position);
        //doorServo2.setPosition(1 - position);
    }
}
