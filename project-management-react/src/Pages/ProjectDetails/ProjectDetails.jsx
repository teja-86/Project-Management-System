import React, {useEffect} from 'react';
import {ScrollArea} from "@/components/ui/scroll-area.jsx";
import {Avatar, AvatarFallback} from "@/components/ui/avatar.jsx";
import {Badge} from "@/components/ui/badge.jsx";
import {Dialog, DialogClose, DialogContent, DialogHeader, DialogTrigger} from "@/components/ui/dialog.jsx";
import {PlusIcon} from "@radix-ui/react-icons";
import InviteUserForm from "@/Pages/ProjectDetails/InviteUserForm.jsx";
import {Button} from "@/components/ui/button.jsx";
import IssueList from "@/Pages/ProjectDetails/IssueList.jsx";
import ChatBox from "@/Pages/ProjectDetails/ChatBox.jsx";
import {fetchProjectById} from "@/Redux/Project/Action.js";
import {useDispatch, useSelector} from "react-redux";
import {useParams} from "react-router-dom";

const ProjectDetails = () => {

    const dispatch = useDispatch();

    const {project} = useSelector(Store => Store);

    const {id} = useParams();

    useEffect(() => {
        dispatch(fetchProjectById(id));
    }, [id]);

    const handleProjectInvitation = () =>{

    }

    return (
        <>
            <div className="mt-5 lg:px-10">
                <div className="lg:flex gap-5 justify-between pb-4">
                    <ScrollArea className="h-screen lg:w-[69%] pr-2 text-left">
                        <div className="text-gray-400 pb-10 w-full">
                            <h1 className="text-lg font-semibold pb-5">
                                {project.projectDetails?.projectName}
                            </h1>

                            <div className="space-y-5 pb-10 ">
                                <p className="w-full md:max-w-lg lg:max-w-xl">
                                    {project.projectDetails?.description}
                                </p>

                                {/*<div className="flex">*/}
                                {/*    <p className="w-36">Project Lead: </p>*/}
                                {/*    <p>{project.projectDetails?.owner.fullName}</p>*/}
                                {/*</div>*/}

                                <div className="flex">
                                    <p className="w-36">Project Lead: </p>
                                    <Badge>{project.projectDetails?.owner.fullName}</Badge>
                                </div>

                                <div className="flex">
                                    <p className="w-36">Members: </p>
                                    <div className="flex items-center gap-2">
                                        {project.projectDetails?.team.map((item) => (
                                            <Avatar className="cursor-pointer" key={item}>
                                                <AvatarFallback>{item.fullName[0]}</AvatarFallback>
                                            </Avatar>
                                        ))}
                                    </div>

                                    <Dialog>

                                        <DialogTrigger>
                                            <DialogClose>
                                                <Button size="sm" variant="outline" onClick={handleProjectInvitation}
                                                        className="ml-2">
                                                    <span>Invite</span>
                                                    <PlusIcon className="w-3 h-3"/>
                                                </Button>
                                            </DialogClose>
                                        </DialogTrigger>

                                        <DialogContent>
                                            <DialogHeader>Invite User</DialogHeader>
                                            <InviteUserForm/>
                                        </DialogContent>

                                    </Dialog>
                                </div>

                                <div className="flex">
                                    <p className="w-36">Category: </p>
                                    <p>{project.projectDetails?.category}</p>
                                </div>

                            </div>

                            <section>
                                <p className="py-5 border-b text-lg tracking-wider">Tasks</p>
                                <div className="lg:flex md:flex gap-3 justify-between py-5">

                                <IssueList status="pending" title="To-Do List"/>
                                    <IssueList status="in_progress" title="In Progress"/>
                                    <IssueList status="done" title="Done"/>

                                </div>
                            </section>
                        </div>
                    </ScrollArea>

                    <div className="lg:w-[30%] rounded-md sticky right-5 top-10">
                        <ChatBox/>
                    </div>

                </div>
            </div>
        </>
    );
};

export default ProjectDetails;