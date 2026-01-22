import React from 'react';
import {Card, CardContent, CardHeader, CardTitle} from "@/components/ui/card.jsx";
import {
    DropdownMenu,
    DropdownMenuContent,
    DropdownMenuItem,
    DropdownMenuTrigger
} from "@/components/ui/dropdown-menu.jsx";
import {Button} from "@/components/ui/button.jsx";
import {DotsVerticalIcon, PersonIcon} from "@radix-ui/react-icons";
import {Avatar, AvatarFallback} from "@/components/ui/avatar.jsx";
import UserList from "@/Pages/ProjectDetails/UserList.jsx";
import {useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import {deleteIssue, updateIssueStatus} from "@/Redux/Issue/Action.js";

const IssueCard = ({item, projectID}) => {

    const navigate = useNavigate();

    const dispatch = useDispatch();

    const handleIssueDelete = () => {
        dispatch(deleteIssue(item.id))
    }

    const handleStatusUpdate = (status) => {
        dispatch(updateIssueStatus({id: item.id, status}));
    };

    return (
        <Card className="rounded-md py-1 pb-2">
            <CardHeader className="py-0 pb1">
                <div className="flex justify-between items-center">
                    <CardTitle className="cursor-pointer" onClick={() =>
                        navigate(`/project/${projectID}/issue/${item.id}`)}>
                        {item.title}
                    </CardTitle>

                    <DropdownMenu>
                        <DropdownMenuTrigger>
                            <Button className="rounded-full" size="icon" variant="ghost">
                                <DotsVerticalIcon/>
                            </Button>
                        </DropdownMenuTrigger>

                        <DropdownMenuContent>
                            <DropdownMenuItem onClick={() => handleStatusUpdate('in_progress')}>In Progress</DropdownMenuItem>
                            <DropdownMenuItem onClick={() => handleStatusUpdate('done')}>Done</DropdownMenuItem>
                            <DropdownMenuItem>Edit</DropdownMenuItem>
                            <DropdownMenuItem onClick={handleIssueDelete}>Delete</DropdownMenuItem>
                        </DropdownMenuContent>
                    </DropdownMenu>
                </div>
            </CardHeader>

            <CardContent className="py-0">
                <div className="flex items-center justify-between">
                    <p>FBP - {1}</p>
                    <DropdownMenu className="w-[30rem] border border-red-400">
                        <DropdownMenuTrigger>
                            <Button
                                size="icon"
                                className="bg-gray-900 hover:text-black text-white rounded-full">
                                <Avatar>
                                    <AvatarFallback>
                                        <PersonIcon/>
                                    </AvatarFallback>
                                </Avatar>
                            </Button>
                        </DropdownMenuTrigger>
                        <DropdownMenuContent>
                            <UserList issueDetails={item}/>
                        </DropdownMenuContent>
                    </DropdownMenu>

                </div>
            </CardContent>
        </Card>
    )
}

export default IssueCard;