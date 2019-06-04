import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Interevntion } from 'app/shared/model/interevntion.model';
import { InterevntionService } from './interevntion.service';
import { InterevntionComponent } from './interevntion.component';
import { InterevntionDetailComponent } from './interevntion-detail.component';
import { InterevntionUpdateComponent } from './interevntion-update.component';
import { InterevntionDeletePopupComponent } from './interevntion-delete-dialog.component';
import { IInterevntion } from 'app/shared/model/interevntion.model';

@Injectable({ providedIn: 'root' })
export class InterevntionResolve implements Resolve<IInterevntion> {
    constructor(private service: InterevntionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IInterevntion> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Interevntion>) => response.ok),
                map((interevntion: HttpResponse<Interevntion>) => interevntion.body)
            );
        }
        return of(new Interevntion());
    }
}

export const interevntionRoute: Routes = [
    {
        path: '',
        component: InterevntionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Interevntions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: InterevntionDetailComponent,
        resolve: {
            interevntion: InterevntionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Interevntions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: InterevntionUpdateComponent,
        resolve: {
            interevntion: InterevntionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Interevntions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: InterevntionUpdateComponent,
        resolve: {
            interevntion: InterevntionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Interevntions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const interevntionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: InterevntionDeletePopupComponent,
        resolve: {
            interevntion: InterevntionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Interevntions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
