import React, {useEffect} from 'react';
import SubscriptionCard from "@/Pages/Subscription/SubscriptionCard.jsx";
import {useDispatch, useSelector} from "react-redux";
import {getUserSubscription, upgradeSubscription} from "@/Redux/Subscription/Action.js";


const paidPlan = [
    "Add unlimited projects",
    "Access live chat support",
    "Add unlimited team members",
    "Advanced reporting features",
    "Priority support",
    "Customization options",
    "Integration support",
    "Advanced security measures",
    "Training and resources",
    "Access control management",
    "Custom workflows",
];

const annualPlan = [
    "Add unlimited projects",
    "Access live chat support",
    "Add unlimited team members",
    "Advanced reporting features",
    "Priority support",
    "Includes everything in the monthly plan",
];

const freePlan = [
    "Add up to 3 projects",
    "Basic task management",
    "Project collaboration tools",
    "Basic reporting features",
    "Email notifications",
    "Basic access control",
];

const Subscription = () => {

    const dispatch = useDispatch();

    const {subscription} = useSelector(Store=>Store);

    useEffect(() => {
        dispatch(getUserSubscription());
    }, [dispatch]);

    return (
        <div className="p-10">
            <h1 className="text-5xl font-semibold py-5 pb-16 text-center">Pricing</h1>
            <div className="flex flex-col lg:flex-row justify-center items-center gap-9">

                <SubscriptionCard
                    data={{
                        planName:"Free",
                        features:freePlan,
                        planType:"FREE",
                        price:0,
                        buttonName: subscription.userSubscription?.planType === "FREE" ? "Current Plan" : "Get Started"
                    }}
                />

                <SubscriptionCard
                    data={{
                        planName:"Monthly Paid Plan",
                        features:paidPlan,
                        planType:"MONTHLY",
                        price:1,
                        buttonName: subscription.userSubscription?.planType === "MONTHLY" ? "Current Plan" : "Get Started"
                    }}
                />

                <SubscriptionCard
                    data={{
                        planName:"Annual Paid Plan",
                        features:annualPlan,
                        planType:"ANNUALLY",
                        price:8.4,
                        buttonName: subscription.userSubscription?.planType === "ANNUALLY" ? "Current Plan" : "Get Started"
                    }}
                />

            </div>

        </div>
    )
}

export default Subscription;