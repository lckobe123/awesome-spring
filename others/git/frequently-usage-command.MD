# git 操作中常用的命令

* 删除名为 'branch-name' 的远程分支
```$xslt
    git push origin --delete branch-name
```
* 移除那些远程仓库中已经删除但本地还存在的分支
```$xslt
    git remote prune origin
    
    此命令可以和git branch -a , git remote show origin 配合使用
```

* 查询当前仓库的所有分支
```$xslt
    git branch -a
```

* 查看远程仓库的分支详细信息
```$xslt
    git remote show origin
```

* 分支变基
```$xslt
    git pull --rebase origin develop
    
    vs
    
    git rebase develop
    
    后者是基于本地的分支做rebase，前者则是以rebase的行为变基
```