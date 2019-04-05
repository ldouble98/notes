# git学习笔记
- **git回滚**
    
        1 文件操作和对应的区变化
        文件 | 文件修改后 | add  | add后   | commit（提交） | commit后
        |  工作区       |          | 暂存区 |                            |当前分支（head）

- **具体操作**

        add 之前的状态还原 git checkout -- [文件名]
        add 之后的文件还原 git reset --hard HEAD 再git checkout -- [文件名]
        commit 之后的文 件还原 git reset --hard HEAD^或 git reset --hard [版本号]
-  

        正常操作是先git status 通过git状态选择对应的回退操作
        实际工作中没这么细致, 有时是技术不到家，有时是想保留备份，通常都会先提交保留一个版本，然后选择是否回退,虽然很少会回到未来~
