// noinspection JSValidateTypes

import React, {useState} from 'react';
import {Card, CardContent} from "@/components/ui/card.jsx";
import {Button} from "@/components/ui/button.jsx";
import {MagnifyingGlassIcon, MixerHorizontalIcon} from "@radix-ui/react-icons";
import {RadioGroup, RadioGroupItem} from "@/components/ui/radio-group.jsx";
import {Input} from "@/components/ui/input.jsx";
import ProjectCard from "@/Pages/Projects/ProjectCard.jsx";
import {ScrollArea} from "@/components/ui/scroll-area.jsx";
import {Label} from "@/components/ui/label.jsx";
import {useDispatch, useSelector} from "react-redux";
import {fetchProjects, searchProjects} from "@/Redux/Project/Action.js";

const ProjectList = () => {

    const dispatch = useDispatch();

    const [keyword, setKeyword] = useState("");

    const {project} = useSelector(Store => Store)

    const handleFilterCategory = (value) => {
        if (value === "All") {
            dispatch(fetchProjects({}));
        }
        else
        dispatch(fetchProjects({category: value}));
    }

    const handleFilterTag = (value) => {
        if (value === "All") {
            dispatch(fetchProjects({}));
        }
        else
        dispatch(fetchProjects({tag: value}));
    }

    const handleSearchChange=(e)=>{
        setKeyword(e.target.value)
        dispatch(searchProjects(e.target.value))
    }

    console.log("Project Store", project)

    return (
        <>
            <div className= 'relative px-5 lg:px-0 lg:flex gap-5 justify-center py-5'>
                <section className = 'filter section'>
                    <Card className = "p-5 sticky top-10">
                        <div className = "flex justify-between lg:w-[20rem]">
                            <p className = 'text-xl -tracking-wider' >Filters</p>
                            <Button variant = "ghost" size="icon">
                                <MixerHorizontalIcon/>
                            </Button>
                        </div>
                        <CardContent className="mt-5">
                            <ScrollArea className="space-y-7 h-[70vh]">
                                <div>
                                    <h1 className="pb-3 text-gray-400 border-b">Category</h1>
                                    <div className="pt-4">
                                        <RadioGroup
                                            className="space-y-3 pt-5"
                                            defaultValue="all"
                                            onValueChange={(value) => handleFilterCategory(value)}>
                                            <div className="flex items-center gap-2">
                                                <RadioGroupItem value='All' id='r1'/>
                                                <Label htmlFor='r1'>All</Label>
                                            </div>
                                            <div className="flex items-center gap-2">
                                                <RadioGroupItem value='Full-Stack' id='r2'/>
                                                <Label htmlFor='r2'>Full-Stack</Label>
                                            </div>
                                            <div className="flex items-center gap-2">
                                                <RadioGroupItem value='Front-End' id='r3'/>
                                                <Label htmlFor='r3'>Front-End</Label>
                                            </div>
                                            <div className="flex items-center gap-2">
                                                <RadioGroupItem value='Back-End' id='r4'/>
                                                <Label htmlFor='r4'>Back-End</Label>
                                            </div>
                                        </RadioGroup>
                                    </div>

                                    <h2 className="pt-10 pb-3 text-gray-400 border-b">Tags</h2>
                                    <div className="pt-4">
                                        <RadioGroup
                                            className="space-y-3 pt-5"
                                            defaultValue="all"
                                            onValueChange={(value) => handleFilterTag(value)}>

                                            {tags.map((item) => <div key={item} className="flex items-center gap-2">
                                                <RadioGroupItem value={item} id={`r1-${item}`}/>
                                                <Label htmlFor={`r1-${item}`}>{item}</Label>
                                            </div>)}
                                        </RadioGroup>
                                    </div>
                                </div>
                            </ScrollArea>
                        </CardContent>
                    </Card>
                </section>
                <section className='projectListSection w-full lg:w-[48rem]'>
                    <div className="flex gap-2 items-center pb-5 justify-between">

                        <div className="relative p-0 w-full">
                            <Input
                                onChange={handleSearchChange}
                            placeholder="Search Project"
                            className="40% px-9"/>
                            <MagnifyingGlassIcon className="absolute top-3 left-4"/>

                        </div>

                    </div>

                    <div>
                        <div className="space-y-5 min-h-[74vh] text-left">
                            {
                                keyword?
                                project.searchProjects.map((item, index) => <ProjectCard item={item} key={item.id*index}/>):
                                    project.projects.map((item) => <ProjectCard key={item.id} item={item}/>)
                            }
                        </div>
                    </div>
                </section>
            </div>
        </>
    )
}

export const tags = [
    "All",
    "React",
    "Next.js",
    "Angular",
    "Flask",
    "Spring Boot",
    "Node.js",
    "Django",
    "MySQL",
    "MongoDB",
    "Java",
    "Python"
];

export default ProjectList;