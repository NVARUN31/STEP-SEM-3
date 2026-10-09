interface MembershipPlan {
    String getName();
    double calculateFee();
}

class MonthlyPlan implements MembershipPlan {
    public String getName() { return "Monthly"; }
    public double calculateFee() { return 1000; }
}

class QuarterlyPlan implements MembershipPlan {
    public String getName() { return "Quarterly"; }
    public double calculateFee() { return 2700; }
}

class AnnualPlan implements MembershipPlan {
    public String getName() { return "Annual"; }
    public double calculateFee() { return 9000; }
}

class Member {
    private final String name;
    Member(String name) { this.name = name; }
    String getName() { return name; }
}

class Membership {
    private final Member member;
    private final MembershipPlan plan;
    private String status = "Active";

    Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: Active.%n",
            plan.getName(), member.getName(), plan.calculateFee());
    }

    void checkIn() {
        if (status.equals("Active"))
            System.out.println(member.getName() + " checked in successfully.");
        else
            System.out.println("Check-in denied: " + member.getName()
                + "'s membership is " + status + ".");
    }

    void freeze() {
        if (status.equals("Active")) {
            status = "Frozen";
            System.out.println(member.getName() + "'s membership frozen.");
            System.out.println("Status: Frozen.");
        } else if (status.equals("Expired")) {
            System.out.println("Cannot freeze an Expired membership.");
        } else {
            System.out.println("Membership is already Frozen.");
        }
    }

    void unfreeze() {
        if (status.equals("Frozen")) {
            status = "Active";
            System.out.println(member.getName() + "'s membership unfrozen.");
        } else {
            System.out.println("Cannot unfreeze membership with status " + status + ".");
        }
    }

    void expire() {
        status = "Expired";
        System.out.println(member.getName() + "'s membership expired.");
        System.out.println("Status: Expired.");
    }
}

public class FitZoneMembershipDesk {
    public static void main(String[] args) {
        Membership asha = new Membership(new Member("Asha"), new QuarterlyPlan());
        Membership ravi = new Membership(new Member("Ravi"), new MonthlyPlan());

        asha.checkIn();
        asha.freeze();
        asha.checkIn();

        ravi.expire();
        ravi.freeze();
    }
}
