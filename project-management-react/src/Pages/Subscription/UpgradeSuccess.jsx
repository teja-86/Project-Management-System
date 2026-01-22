import {Card} from "@/components/ui/card.jsx";
import {Button} from "@/components/ui/button.jsx";
import {useNavigate} from "react-router-dom";
import {CheckCircledIcon} from "@radix-ui/react-icons";
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";
import {upgradeSubscription} from "@/Redux/Subscription/Action.js";

const UpgradeSuccess = () => {

    const dispatch = useDispatch();

    const navigate = useNavigate();

    const {subscription} = useSelector(Store => Store);

    const queryParams = new URLSearchParams(location.search);

    const planType = queryParams.get("planType");

    useEffect(() => {
        dispatch(upgradeSubscription({planType}));
    }, [dispatch, planType]);

    return (
        <div className="flex justify-center">
            <Card className="mt-20 p-5 space-y-5 flex flex-col items-center">
                <div className="flex items-center gap-4">

                    <CheckCircledIcon className="h-9 w-9 text-green-500"/>
                    <p className="text-xl">Plan Upgraded Successfully!</p>

                </div>

                <div className="space-y-3">

                    <p className="text-green-500">Start Date: {subscription.userSubscription?.subscriptionStartDate}</p>
                    <p className="text-red-500">End Date: {subscription.userSubscription?.subscriptionEndDate}</p>
                    <p>Plan Type: {subscription.userSubscription?.planType}</p>

                    <Button onClick={() => navigate("/")}>Home Page</Button>

                </div>
            </Card>
        </div>
    )
}

export default UpgradeSuccess;