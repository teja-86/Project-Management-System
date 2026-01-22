import React, {useEffect} from 'react';
import {Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger} from "@/components/ui/dialog.jsx";
import {Card, CardContent, CardFooter, CardHeader, CardTitle} from "@/components/ui/card.jsx";
import IssueCard from "@/Pages/ProjectDetails/IssueCard.jsx";
import {Button} from "@/components/ui/button.jsx";
import {PlusIcon} from "@radix-ui/react-icons";
import CreateIssueForm from "@/Pages/ProjectDetails/CreateIssueForm.jsx";
import {useDispatch, useSelector} from "react-redux";
import {fetchIssues} from "@/Redux/Issue/Action.js";
import {useParams} from "react-router-dom";

const IssueList = ({title, status}) => {

    const dispatch = useDispatch();

    const {issue} = useSelector(Store => Store);

    const {id} = useParams();

    useEffect(() => {
        dispatch(fetchIssues(id))
    },[dispatch, id]);

    return (
        <div>
            <Dialog>
                <Card className="w-full md:w-[300px] lg:w-[310px]">
                    <CardHeader className="">
                        <CardTitle>{title}</CardTitle>
                    </CardHeader>
                    <CardContent>
                        <div className="space-y-2">
                            {issue.issues
                                .filter(item => item.status.toLowerCase() === status.toLowerCase())
                                .map(item => (
                                    <IssueCard projectID={id} item={item} key={item.id} />
                                ))}
                        </div>
                    </CardContent>
                    <CardFooter>
                        <DialogTrigger>
                            <Button variant="outline" className="w-full flex items-center gap-2"><PlusIcon/>Create Issue</Button>

                        </DialogTrigger>
                    </CardFooter>
                </Card>
                <DialogContent>
                    <DialogHeader>
                        <DialogTitle>Create New Issue</DialogTitle>
                    </DialogHeader>
                    <CreateIssueForm status={status} />
                </DialogContent>
            </Dialog>

        </div>
    )
}

export default IssueList;