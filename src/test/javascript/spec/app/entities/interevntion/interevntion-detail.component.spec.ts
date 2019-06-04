/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { InterevntionDetailComponent } from 'app/entities/interevntion/interevntion-detail.component';
import { Interevntion } from 'app/shared/model/interevntion.model';

describe('Component Tests', () => {
    describe('Interevntion Management Detail Component', () => {
        let comp: InterevntionDetailComponent;
        let fixture: ComponentFixture<InterevntionDetailComponent>;
        const route = ({ data: of({ interevntion: new Interevntion(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [InterevntionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(InterevntionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InterevntionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.interevntion).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
